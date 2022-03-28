# StudentCenter
## MySQL
Version: MySQL 5.5\
IP: 49.232.12.245\
Port: 3306\
Username: StudentCenter\
Password: 123456

## Server
IP: 127.0.0.1\
Port: 9555

## Entity
尽量保证entity里的Class的名字和表的名字一致\
尽量保证Class里的Field的名字和表中列的名字一致\
（否则需要添加注解取别名）

## Mapper
@Mapper必须写在每个Mapper接口之上
```java
@Mapper
public interface XXXMapper {}
```

## Controller
@RestController必须写在每个Controller类之上\
@RestController会将该类下所有的对象类型的返回值包装成JSON发送给浏览器\
@Autowired用在一个set方法之上，spring会自动调用该set方法为我们创建Mapper
```java
@RestController
public class StudentController {
    private StudentMapper studentMapper;
    
    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }
}
```


