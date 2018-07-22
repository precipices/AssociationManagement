package cn.wk.association.entity;

import java.util.Date;

//--新闻及活动表
//if(exists (select * from sys.objects where name = 'activity'))
//	drop table activity
//create table activity(
//	id varchar(50) primary key,			--新闻/活动编号
//	association_id varchar(50) not null,--社团编号
//	release_date datetime not null,		--发布日期
//	title nvarchar(50) not null,		--标题
//	content nvarchar(4000) not null,	--内容
//	headpic varchar(100),				--标识图片
//	type nvarchar(50) not null,			--类别(活动、公告或新闻等)
//)
public class Activity {
	private String id;
	private String association_id;
	private Date releaseDate;
	private String title;
	private String content;
	private String headpic;
	private String type;// 类别(活动、公告或新闻等)

	public Activity() {
		super();
	}

	public Activity(String association_id, Date releaseDate, String title, String content, String type) {
		super();
		this.association_id = association_id;
		this.releaseDate = releaseDate;
		this.title = title;
		this.content = content;
		this.type = type;
	}

	public Activity(String id, String association_id, Date releaseDate, String title, String headpic,
			String type) {
		super();
		this.id = id;
		this.association_id = association_id;
		this.releaseDate = releaseDate;
		this.title = title;
		this.headpic = headpic;
		this.type = type;
	}
	public Activity(String id, String association_id, Date releaseDate, String title, String content, String headpic,
			String type) {
		super();
		this.id = id;
		this.association_id = association_id;
		this.releaseDate = releaseDate;
		this.title = title;
		this.content = content;
		this.headpic = headpic;
		this.type = type;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", association_id=" + association_id + ", releaseDate=" + releaseDate + ", title="
				+ title + ", content=" + content + ", headpic=" + headpic + ", type=" + type + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAssociation_id() {
		return association_id;
	}

	public void setAssociation_id(String association_id) {
		this.association_id = association_id;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHeadpic() {
		return headpic;
	}

	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
