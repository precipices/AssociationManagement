package cn.wk.association.entity;
//--用户表
//create table customer(
//	username nvarchar(50) primary key,	--用户名
//	password varchar(50) not null,		--密码
//	id varchar(50) unique not null,		--学号（不可修改）
//	realname nvarchar(50) not null,		--真实姓名
//	classname nvarchar(50),				--班级
//	usertype nvarchar(50) not null,		--用户类型
//	contact varchar(50) ,				--联系方式
//	headpic varchar(100)				--头像
//)
public class Customer {
	private String username;
	private String password;
	private String id;
	private String realname;
	private String classname;
	private String usertype;
	private String contact;
	private String headpic;
	public Customer(String username, String password, String id, String realname, String usertype) {
		this.username = username;
		this.password = password;
		this.id = id;
		this.realname = realname;
		this.usertype = usertype;
	}
	public Customer(String username, String password, String id, String realname, String classname, String usertype,
			String contact, String headpic) {
		this.username = username;
		this.password = password;
		this.id = id;
		this.realname = realname;
		this.classname = classname;
		this.usertype = usertype;
		this.contact = contact;
		this.headpic = headpic;
	}
	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", id=" + id + ", realname=" + realname
				+ ", classname=" + classname + ", usertype=" + usertype + ", contact=" + contact + ", headpic="
				+ headpic + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getHeadpic() {
		return headpic;
	}
	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}
}
