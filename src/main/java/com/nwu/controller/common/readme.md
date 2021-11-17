## 公共控制器
* 关于公共方法的各项控制器，需要在 controller 上添加 mapping
* 包括申请状态变更等等公共部分的方法
* 包括获取公共属性的方法也可在此下面进行填写

```java
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common") // 可以建多个 controller，但需要每个与公共方法相关的控制器均添加此 mapper 前缀
public class XxxController {
    
    /*
        后续方法继续填写
     */
    public String xx(){
        return "Hello";
    }
}
```