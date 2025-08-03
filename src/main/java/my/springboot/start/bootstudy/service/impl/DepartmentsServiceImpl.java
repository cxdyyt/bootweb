package my.springboot.start.bootstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.springboot.start.bootstudy.entity.Departments;
import my.springboot.start.bootstudy.service.DepartmentsService;
import my.springboot.start.bootstudy.mapper.DepartmentsMapper;
import org.springframework.stereotype.Service;

/**
* @author XY
* @description 针对表【departments】的数据库操作Service实现
* @createDate 2025-11-09 21:41:40
*/
@Service
public class DepartmentsServiceImpl extends ServiceImpl<DepartmentsMapper, Departments>
    implements DepartmentsService{

}




