package dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import pojo.Picture;

import java.util.List;

/**
 * Created by apple on 2018/6/5.
 */
class pictureDAOImpl extends HibernateTemplate implements pictureDAO {
    @Override
    public void addPicture(Picture picture) {
        save(picture);
    }

    @Override
    public void deletePicture(Picture picture) {
        super.delete(picture);
    }

    @Override
    public void updatePicture(Picture picture) {
        super.update(picture);
    }

    @Override
    public List<Picture> list() {
        return find("from Picture ");
    }

    @Override
    public Picture get(int id) {
        return (Picture)get(Picture.class,id);
    }
}
