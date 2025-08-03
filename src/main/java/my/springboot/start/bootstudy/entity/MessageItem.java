package my.springboot.start.bootstudy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

@TableName("message_items")  // 指定对应的数据库表名
public class MessageItem {
    @TableId(type = IdType.AUTO)  // 指定为主键字段，并使用自增策略
    private Long id; // database auto id
    private String messageId; // unique message id for idempotency
    private String payload;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}


