package rs.raf.demo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.demo.entities.Subject;
import rs.raf.demo.entities.User;
import rs.raf.demo.repositories.user.UserRepository;

import javax.inject.Inject;
import java.net.PasswordAuthentication;
import java.util.Date;
import java.util.List;

public class UserService {

    @Inject
    UserRepository userRepository;

    public String register(String username, String role){
        //da li je unos validan

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");
        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(username)
                .withClaim("role", role)
                .sign(algorithm);
    }

    public List<User> allUsers(){
       return this.userRepository.allUsers();
    }

    public String login(String username, String password)
    {
        System.out.println("Password je " + password);
        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = this.userRepository.findUser(username);

        System.out.println("H Password je " + hashedPassword);
        if (user == null || !user.getHashedPassword().equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // JWT-om mozete bezbedono poslati informacije na FE
        // Tako sto sve sto zelite da posaljete zapakujete u claims mapu
        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(username)
                .withClaim("role", user.getRole())
                .sign(algorithm);
    }
    public User updateUser(User user) {return this.userRepository.updateUser(user);}
    public User addUser(User user) {
        return this.userRepository.addUser(user);
    }
    public User findUser(String username) {
        return this.userRepository.findUser(username);
    }
    public int changeStatus ( int id, int status) { return this.userRepository.changeStatus(id, status);}
    public boolean isAuthorized(String token){

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        String username = jwt.getSubject();
//        jwt.getClaim("role").asString();

        User user = this.userRepository.findUser(username);


        if (user == null){
            return false;
        }

        return true;
    }
}
