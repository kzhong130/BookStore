package dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.QueryDao;
import model.Order;
import model.Query;

public class QueryDaoImpl extends HibernateDaoSupport implements QueryDao{

	
	public List<Query> getAllQuery() {
		@SuppressWarnings("unchecked")
		List<Query> querys = (List<Query>) getHibernateTemplate().find("from Query");
		return querys;
	}

	@Override
	public void QueryAll() {
		//System.out.println("mdd");
		Session session=this.getSession();
	      //session.beginTransaction();             
	       PreparedStatement st = null;
		try {
			st = session.connection().prepareStatement(             
			          "{call QueryAll}");
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}             

	       try {
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void QueryByUser(int userid) {
		
		Session session=this.getSession();
	      //session.beginTransaction();             
	       PreparedStatement st = null;
		try {
			st = session.connection().prepareStatement(             
			          "{call QueryByUser(?)}");
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}             
	       try {
			st.setInt(1, userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       try {
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	public void QueryByBook(int bookid) {
		
		Session session=this.getSession();
	      //session.beginTransaction();             
	       PreparedStatement st = null;
		try {
			st = session.connection().prepareStatement(             
			          "{call QueryByBook(?)}");
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}             
	       try {
			st.setInt(1, bookid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       try {
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       //session.getTransaction().commit();  

		
	}

	@Override
	public void QueryByCatagory(String catagory) {
		
		Session session=this.getSession();
	      //session.beginTransaction();             
	       PreparedStatement st = null;
		try {
			st = session.connection().prepareStatement(             
			          "{call QueryByCatagory(?)}");
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}             
	       try {
			st.setString(1, catagory);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       try {
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       //session.getTransaction().commit();  
	    
		
	}

	@Override
	public void QueryByTime(String startTime, String endTime) {
		
		Session session=this.getSession();
	      //session.beginTransaction();             
	       PreparedStatement st = null;
		try {
			st = session.connection().prepareStatement(             
			          "{call QueryByTime(?,?)}");
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}             
	       try {
			st.setString(1, startTime);
			st.setString(2, endTime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       try {
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	

}
