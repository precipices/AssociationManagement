package cn.wk.association.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wk.association.config.DaoConfig;
import cn.wk.association.dao.AssociationDao;
import cn.wk.association.entity.Association;
import cn.wk.association.entity.Association;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoConfig.class)
public class AssociationDaoTest {
	@Autowired
	public AssociationDao associationDao;

	@Test
	public void getAllAssociations() {
		System.out.println("Begin-----------getAllAssociations-------------");

		List<Association> associations = associationDao.getAllAssociations();
		for (int i = 0; i < associations.size(); i++) {
			System.out.println(associations.get(i));
		}
		System.out.println("End-------------getAllAssociations-------------");
	}

	@Test
	public void addAssociation() {
		System.out.println("Begin-----------addAssociation-------------");
		associationDao.addAssociation("3", "上理跆拳道社", "健身",
				"上理跆拳道社成立于2000年，属于体育类社团，至今风雨十七载，已逐渐发展成为我校的几个颇具影响力的社团之一。现指导老师为体育部索红杰老师。社团现任教练有宋奇、陆逸风，助教徐一帆。宋奇教练是韩国国技院WTF黑带三段，我社创始人，毕业后开设了“传奇跆拳道馆”，已先后培养出三十三名WTF黑带一段。",
				"images/association/3.jpg", null, "wk");

		System.out.println("End-------------addAssociation-------------");
	}

	@Test
	public void deleteAssociation() {
		System.out.println("Begin-----------deleteAssociation-------------");
		associationDao.deleteAssociation("3");
		System.out.println("End-------------deleteAssociation-------------");
	}

	@Test
	public void getAssociation() {
		System.out.println("Begin-----------getAssociation-------------");
		System.out.println(associationDao.getAssociation("上理跆拳道社"));
		System.out.println("End-------------getAssociation-------------");
	}

	@Test
	public void getAssociationById() {
		System.out.println("Begin-----------getAssociationById-------------");
		System.out.println(associationDao.getAssociationById("3"));
		System.out.println("End-------------getAssociationById-------------");
	}

	@Test
	public void getMyAssociations() {
		System.out.println("Begin-----------getMyAssociations-------------");

		List<Association> associations = associationDao.getMyAssociations("wk");
		for (int i = 0; i < associations.size(); i++) {
			System.out.println(associations.get(i));
		}
		System.out.println("End-------------getMyAssociations-------------");
	}
	@Test
	public void addRelationship() {
		System.out.println("Begin-----------addRelationship-------------");

		System.out.println(associationDao.addRelationship("wk","99207a95-01c5-4b8b-bf4e-be72f825f495"));
		
		System.out.println("End-------------addRelationship-------------");
	}
}
