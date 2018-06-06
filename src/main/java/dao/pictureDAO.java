package dao;

import pojo.Picture;

import java.util.List;

/**
 * Created by apple on 2018/6/5.
 */
public interface pictureDAO {
    public void addPicture(Picture picture);
    public void deletePicture(Picture picture);
    public void updatePicture(Picture picture);
    public List<Picture> list();
    public Picture get(int id);
}
