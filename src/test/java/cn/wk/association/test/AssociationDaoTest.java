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
		associationDao.addAssociation("3", "������ȭ����", "����",
				"������ȭ���������2000�꣬�������������ţ��������ʮ���أ����𽥷�չ��Ϊ��У�ļ����ľ�Ӱ����������֮һ����ָ����ʦΪ�������������ʦ���������ν��������桢½�ݷ磬������һ������������Ǻ�������ԺWTF�ڴ����Σ����紴ʼ�ˣ���ҵ�����ˡ�������ȭ���ݡ������Ⱥ���������ʮ����WTF�ڴ�һ�Ρ�",
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
		System.out.println(associationDao.getAssociation("������ȭ����"));
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
