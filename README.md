# StudentCenter
## MySQL
Version: `MySQL 5.7`\
IP: `studentcenter.mysql.database.azure.com`\
Port: `3306`\
Username: `scenter`\
Password: `StudentCenter9555`

## Server
IP: 127.0.0.1\
Port: 9555

## Config file
`prefix: classpath:/templates/`\
`suffix: .html`\
会将Controller的`mv.setViewName("allstudents")`\
自动补全为`"/templates/allstudents.html"`
```yaml
# port
server:
  port: 9555

spring:
  # database connection
  datasource:
    url: jdbc:mysql://studentcenter.mysql.database.azure.com:3306/studentcenter
    username: scenter
    password: StudentCenter9555
    driver-class-name: com.mysql.jdbc.Driver
  # thymeleaf config
  thymeleaf:
    cache: false
    prefix: classpath:/templates/
    encoding: UTF-8
    suffix: .html
    mode: HTML
```

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
@RestController会将该类下所有的对象类型的返回值包装成JSON发送给浏览器\
根据不同的需要我们也可以采用Thymeleaf一节中介绍的方法编写Controller\
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

## Thymeleaf
例子见`src/main/resources/templates/allstudents.html`\
Controller: `ThymeleafController.java`\
- 向Spring框架返回ModelAndView
- Thymeleaf用Model中的数据渲染View
- 最后将渲染好的html发送给浏览器
```java
@Controller
public class ThymeleafController {
    private StudentMapper studentMapper;

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @RequestMapping("/allstudents")
    public ModelAndView getAllStudents() {
        ModelAndView mv = new ModelAndView();
        List<Student> students = studentMapper.selectAll();
        mv.addObject("students", students);
        mv.setViewName("allstudents");
        return mv;
    }
}
```

