package com.zsx.web.dao.Impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zsx.web.dao.BaseDaoI;

@Repository
public class BaseDaoImpl<T> implements BaseDaoI<T> {

	@Autowired
	public SessionFactory sessionFactory;

	/**
	 * 获得当前事物的session
	 * 
	 * @return org.hibernate.Session
	 */
	public Session getCurrentSession() {
//		return this.sessionFactory.getCurrentSession();
		//从会话工厂获取一个session
		return this.sessionFactory.openSession();
	}

	public Serializable save(T o) {
		if (o != null) {
			return this.getCurrentSession().save(o);
		}
		return null;
	}

	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	public T get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	public T get(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				//q.setParameter(key, params.get(key));
//				q.setParameter(key, params.get(key));
//				q.setParameter("", "");
//				q.setparameterlist
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	 public void delete(T o) {
		 if (o != null) {
			 this.getCurrentSession().delete(o);
		 }
	 }
	
	 public void update(T o) {
		 if (o != null) {
			 this.getCurrentSession().update(o);
		 }
	 }

	public void saveOrUpdate(T o) {
		if (o != null) {
			this.getCurrentSession().saveOrUpdate(o);
		}
	}

	public List<T> find(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
//				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	public List<T> find(String hql, Map<String, Object> params, int page,
			int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
//				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public List<T> find(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public Long count(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	public int executeHql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	public int executeHql(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	public List<Object[]> findBySql(String sql) {
		SQLQuery q  = this.getCurrentSession().createSQLQuery(sql);
		return q.list();
	}

	public List<Object[]> findBySql(String sql, int page, int rows) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public List<Object[]> findBySql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	public List<Object[]> findBySql(String sql, Map<String, Object> params,
			int page, int rows) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public int executeSql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.executeUpdate();
	}

	public int executeSql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	public BigInteger countBySql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return (BigInteger) q.uniqueResult();
	}

	public BigInteger countBySql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (BigInteger) q.uniqueResult();
	}

	/**
	 * 批量保存数据
	 * 
	 * @param <T>
	 * @param entitys 要持久化的临时实体对象集合
	 */
	public void batchSave(List<T> entitys) throws SQLException {
		for (int i = 0; i < entitys.size(); i++) {
			getCurrentSession().save(entitys.get(i));
			if (i % 20 == 0) {
				// 20个对象后才清理缓存，写入数据库
				getCurrentSession().flush();
				getCurrentSession().clear();
			}
		}
		// 最后清理一下----防止大于20小于40的不保存
		getCurrentSession().flush();
		getCurrentSession().clear();
	}

}
