package pojo;

public class User {
    private int id;
    private String stuId;   //账号
    private String password;  //密码
    private String userName;  //用户名
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "User{" +
                "stuId='" + stuId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
