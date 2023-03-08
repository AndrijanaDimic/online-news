package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Tag {

    private int tagId;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String keyWord;

    public Tag(int tagId, String keyWord) {
        this.keyWord = keyWord;
        this.tagId = tagId;
    }
    public Tag(){

    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", keyWord='" + keyWord + '\'' +
                '}';
    }
}
