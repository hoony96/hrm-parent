package cn.itsource.hrm.service.impl;

import cn.itsource.hrm.client.RedisClient;
import cn.itsource.hrm.domain.CourseType;
import cn.itsource.hrm.mapper.CourseTypeMapper;
import cn.itsource.hrm.service.ICourseTypeService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 *
 * @author hoony96
 * @since 2019-12-25
 */
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {

    @Autowired
    private RedisClient redisClient;

    private final String COURSE_TYPE = "hrm:course_type:all";

    @Override
    public List<CourseType> loadTreeData() {
        List<CourseType> list = new ArrayList<>();
        // 从 redis中查询
        String redisStr = redisClient.get(COURSE_TYPE);
        // 判断
        if(StringUtils.isNotEmpty(redisStr)){
            // 有 就直接返回
            list = JSONObject.parseArray(redisStr, CourseType.class);
        }else{
            // 没有

            // 查数据库
            list = mapAndIter();
            // 保存到 redis 中
            String str = JSONObject.toJSONString(list);
            redisClient.set(COURSE_TYPE,str);
        }
        // 返回数据
        return list;
    }


//    @Override
//    public List<CourseType> loadTreeData() {
//       return recursionWay(0L);
//        return doubleIter();
//        return mapAndIter();
//    }

    /**
     *  第一种方式  使用递归
     * @return
     */
    private List<CourseType> recursionWay(Long pid){
        // 查询 一级课程目录  pid = 0
        List<CourseType> types = baseMapper.selectList(
                new QueryWrapper<CourseType>()
                        .eq("pid", pid));

        // 递归的出口
        // 表示查询出来的 课程都没有  就直接退出
        if(types == null || types.size() == 0){
            return null;
        }

        for (CourseType type : types) {
            // 循环查询出来的 所有菜单  再次 使用该方法进行递归  查询所有的下级目录
            List<CourseType> childs = recursionWay(type.getId());
            // 把查询出来的 子级目录 放入对应的父级目录
            type.setChildren(childs);
        }
        return types;
    }

    /**
     * 第二种 使用嵌套for循环
     * @return
     */
    private List<CourseType> doubleIter(){
        // 一级目录
        List<CourseType> firstLevel = new ArrayList<>();

        List<CourseType> types = baseMapper.selectList(null);
        // 循环查询出来的 所有目录
        for (CourseType type : types) {
            if(type.getPid().longValue() == 0L){
                // 认为市一级目录 装进去
                firstLevel.add(type);
            }else{
                // 否则 认为是子目录 再次循环  找爹
                for (CourseType parent : types) {
                    //  父子相认
                    if(type.getPid().longValue() == parent.getId().longValue()){
                        // 装进 父级目录的child中
                        parent.getChildren().add(type);
                    }
                }
            }
        }
        return firstLevel;
    }

    /**
     *  第三种方式  使用 Map 加 循环的方式
     * @return
     */
    public List<CourseType> mapAndIter(){
        // 一级目录
        List<CourseType> firstLevel = new ArrayList<>();

        List<CourseType> types = baseMapper.selectList(null);

        // 把查询出来的父级菜单装到Map中
        Map<Long,CourseType> map = new HashMap<>();
        for (CourseType type : types) {
            map.put(type.getId(),type);
        }

        for (CourseType type : types) {
            // 一级目录
            if(type.getPid().longValue() == 0L){
                firstLevel.add(type);
            }else{
                // type为子级目录
                CourseType courseType = map.get(type.getPid());  // 获取到父级目录
                // 装入父级目录中
                if(courseType != null){
                    courseType.getChildren().add(type);
                }
            }
        }
        return  firstLevel;
    }


    /**
     *  mysql 与 redis同步
     */
    private void synchronizeOperation() {
        // 查询数据库
        List<CourseType> list = mapAndIter();
        // 保存到redis
        String jsonString = JSONObject.toJSONString(list);
        redisClient.set(COURSE_TYPE,jsonString);
    }


    @Override
    public boolean save(CourseType entity) {
        super.save(entity);
        synchronizeOperation();
        return true;
    }


    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        synchronizeOperation();
        return true;
    }

    @Override
    public boolean updateById(CourseType entity) {
        super.updateById(entity);
        synchronizeOperation();
        return true;
    }
}
