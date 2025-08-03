package my.springboot.start.bootstudy.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import my.springboot.start.bootstudy.entity.Departments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author XY
* @description 针对表【departments】的数据库操作Mapper
* @createDate 2025-11-09 21:41:40
* @Entity my.springboot.start.bootstudy.entity.Departments
*/
public interface DepartmentsMapper extends BaseMapper<Departments> {
    int insertSelective(Departments departments);

    List<Departments> getAllByName(@Param("name") String name);

    List<Departments> getByIdAndName(@Param("id") Integer id, @Param("name") String name);

    int updateSelective(Departments departments);
}




