package mybatis.mapper;

import mybatis.entity.HelloEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface HelloMapper {
    @Select("select * from experiment.hello")
    List<HelloEntity> getAllHello();

    @Insert("insert into experiment.hello (big_value) values (#{bigValue})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int createHello(HelloEntity hello);
}
