package com.example.demo.mapper;

import com.example.demo.bean.Teacher;
import com.example.demo.bean.TeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TeacherMapper {
    @SelectProvider(type=TeacherSqlProvider.class, method="countByExample")
    int countByExample(TeacherExample example);

    @DeleteProvider(type=TeacherSqlProvider.class, method="deleteByExample")
    int deleteByExample(TeacherExample example);

    @Delete({
        "delete from class_teacher",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into class_teacher (id, class_name, ",
        "values (#{id,jdbcType=INTEGER}, #{className,jdbcType=VARCHAR}, ",
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="teacherId", before=false, resultType=Integer.class)
    int insert(Teacher record);

    @InsertProvider(type=TeacherSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="teacherId", before=false, resultType=Integer.class)
    int insertSelective(Teacher record);

    @SelectProvider(type=TeacherSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="class_name", property="className", jdbcType=JdbcType.VARCHAR),
        @Result(column="teacher_id", property="teacherId", jdbcType=JdbcType.INTEGER)
    })
    List<Teacher> selectByExample(TeacherExample example);

    @Select({
        "select",
        "id, class_name, teacher_id",
        "from class_teacher",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="class_name", property="className", jdbcType=JdbcType.VARCHAR),
        @Result(column="teacher_id", property="teacherId", jdbcType=JdbcType.INTEGER)
    })
    Teacher selectByPrimaryKey(Integer id);

    @UpdateProvider(type=TeacherSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Teacher record, @Param("example") TeacherExample example);

    @UpdateProvider(type=TeacherSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Teacher record, @Param("example") TeacherExample example);

    @UpdateProvider(type=TeacherSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Teacher record);

    @Update({
        "update class_teacher",
        "set class_name = #{className,jdbcType=VARCHAR},",
          "teacher_id = #{teacherId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Teacher record);
}