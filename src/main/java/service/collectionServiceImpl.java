package service;

import dao.collectionDAO;
import pojo.Collection;
import pojo.Message;
import pojo.User;

import java.sql.Timestamp;
import java.util.List;

public class collectionServiceImpl implements collectionService {
    collectionDAO collection;

    public collectionDAO getCollection() {
        return collection;
    }

    public void setCollection(collectionDAO collection) {
        this.collection = collection;
    }

    @Override
    public void addCollection(int user_id, int message_id) {
        Collection collection = new Collection();
        User user = new User();
        Message message = new Message();
        user.setUserId(user_id);
        message.setMessageId(message_id);
        collection.setUserByUserId(user);
        collection.setMessageByMessageId(message);
        collection.setCollectionTime(new Timestamp(System.currentTimeMillis()));
        collection.setCollectionStatus("true");
        this.collection.addCollection(collection);
    }

    @Override
    public void deleteCollection(int user_id, int message_id) {
        List<Collection> collections = this.collection.list(user_id,message_id);
        this.collection.deleteCollection(collections.get(0));
    }

    @Override
    public void updateMessage(int message_id, int add) {
        Message message = this.collection.getMessage(message_id);
        if(add == 1)
            message.setMessageCollectnum(message.getMessageCollectnum() + 1);
        else
            message.setMessageCollectnum(message.getMessageCollectnum() - 1);
        this.collection.updateMessage(message);
    }
}
