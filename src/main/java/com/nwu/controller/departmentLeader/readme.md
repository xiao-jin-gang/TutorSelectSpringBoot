## 院系领导
* 关于院系领导的各项控制器，需要在 controller 上添加 mapping

```java
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departmentLeader") // 可以建多个 controller，但需要每个与院系领导相关的控制器均添加此 mapper 前缀
public class XxxController {
    
    /*
        后续方法继续填写
     */
    public String xx(){
        return "Hello";
    }
}
```