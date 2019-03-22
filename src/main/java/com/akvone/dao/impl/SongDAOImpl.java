package com.akvone.dao.impl;

import com.akvone.dao.SongDAO;
import com.akvone.entity.Song;
import lombok.RequiredArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class SongDAOImpl implements SongDAO {

  private final SessionFactory sessionFactory;

  @Override
  public void save(Song song) {
    sessionFactory.getCurrentSession().saveOrUpdate(song);
  }

  @Override
  public boolean exists(Long vkId) {
    boolean exists = false;
    try {
      Criteria songCriteria = sessionFactory.getCurrentSession().createCriteria(Song.class);
      songCriteria.add(Restrictions.eq("vkId", vkId));
      exists = !songCriteria.list().isEmpty();
    } catch (HibernateException ex) {
      return false;
    }
    return exists;
  }

  @Override
  public Song getByVkId(Long vkId) {
    Song song;
    try {
      Criteria songCriteria = sessionFactory.getCurrentSession().createCriteria(Song.class);
      songCriteria.add(Restrictions.eq("vkId", vkId));
      song = (Song) songCriteria.list().get(0);
    } catch (HibernateException ex) {
      return null;
    }
    return song;
  }
}
