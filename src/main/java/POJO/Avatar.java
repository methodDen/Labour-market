package POJO;

public class Avatar implements java.io.Serializable {
    private Integer avatarId;
    private byte[] image;

    public Avatar() {
    }

    public Avatar(byte[] image) {
        this.image = image;
    }

    public Integer getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarId = avatarId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
