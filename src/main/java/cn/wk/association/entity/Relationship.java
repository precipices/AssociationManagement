package cn.wk.association.entity;

import java.util.Date;

//--ѧ�������Ź�ϵ��
//if(exists (select * from sys.objects where name = 'relationship'))
//	drop table relationship
//create table relationship(
//	association_id varchar(50) not null,--���ű��
//	username nvarchar(50) not null, 	--�û���
//	position nvarchar(50) not null,		--ְ��
//	join_time date not null,			--����ʱ��
//)
public class Relationship {
	private String associationId;
	private String username;
	private String position;
	private Date joinTime;
	public Relationship() {
		super();
	}
	public Relationship(String associationId, String username, String position, Date joinTime) {
		super();
		this.associationId = associationId;
		this.username = username;
		this.position = position;
		this.joinTime = joinTime;
	}
	@Override
	public String toString() {
		return "Relationship [associationId=" + associationId + ", username=" + username + ", position=" + position
				+ ", joinTime=" + joinTime + "]";
	}
	public String getAssociationId() {
		return associationId;
	}
	public void setAssociationId(String associationId) {
		this.associationId = associationId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
}
