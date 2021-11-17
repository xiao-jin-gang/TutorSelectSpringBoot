## 管理员公共控制器


```java
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin") // 可以建多个 controller，但需要每个与公共方法相关的控制器均添加此 mapper 前缀
public class XxxController {
    
    /*
        后续方法继续填写
     */
    public String xx(){
        return "Hello";
    }
}
```