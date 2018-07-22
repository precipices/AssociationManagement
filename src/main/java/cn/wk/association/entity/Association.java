package cn.wk.association.entity;

import java.util.List;

//--社团表
//if(exists (select * from sys.objects where name = 'association'))
//	drop table association
//create table association(
//	id varchar(50) primary key,			--社团编号
//	name nvarchar(50) unique not null,	--社团名称
//	type nvarchar(50) not null,			--社团类型
//	information nvarchar(4000) not null,--详情
//	headpic varchar(100),				--标识图片
//	index_html nvarchar(max)			--社团首页(待定)
//)
public class Association {
	private String id;
	private String name;
	private String type;
	private String information;
	private String headpic;
	private String index_html;
	private List<Relationship> relationships;

	public Association() {
		super();
	}

	public Association(String id, String name, String type, String information, String headpic, String index_html,
			List<Relationship> relationships) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.information = information;
		this.headpic = headpic;
		this.index_html = index_html;
		this.relationships = relationships;
	}

	@Override
	public String toString() {
		return "Association [id=" + id + ", name=" + name + ", type=" + type + ", information=" + information
				+ ", headpic=" + headpic + ", index_html=" + index_html + ", relationships=" + relationships + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getHeadpic() {
		return headpic;
	}

	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}

	public String getIndex_html() {
		return index_html;
	}

	public void setIndex_html(String index_html) {
		this.index_html = index_html;
	}

	public List<Relationship> getRelationships() {
		return relationships;
	}

	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
	}
}
