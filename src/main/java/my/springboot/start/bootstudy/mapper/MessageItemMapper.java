package my.springboot.start.bootstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.springboot.start.bootstudy.entity.MessageItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

// MyBatis-Plus会自动扫描，也可以保留@Mapper注解
// @Mapper
@Repository
public interface MessageItemMapper extends BaseMapper<MessageItem> {
    int insertIgnore(MessageItem item);
    MessageItem findByMessageId(@Param("messageId") String messageId);
}


