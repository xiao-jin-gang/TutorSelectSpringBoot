## 修改记录
* 2021.08.08 **Rex Joush**
  * 格式化代码快捷键 `Ctrl + Alt + L`

* 2021.08.08 **Rex Joush**
  * 新建项目，配置基础环境
  * 添加依赖
  * 修改端口号 8080 为 8080

* 2021.08.10 **dynamic10**
  * 修改数据库表system_time中字段time_id为int类型，自增
  * 添加数据库表system_user，存储使用该系统用户的学工号，角色名称，用户状态，院系名称，以及系统用户的创建时间

* 2021.08.13 **Rex Joush**
  * 规范后端代码的文件目录结构
  * 新建 controller 和 service 层的文件夹并附带 readme.md 说明

* 2021.08.18 **dynamic**
  * 规范学科属性代码 ：文科1 理科2 交叉学科3
  * 修改获取导师列表返回值

* 2021.08.18 **zjz**
  * ResultCode成功统一返回20000 失败20001
  * 数据库apply表 id主键改为自增
    
* 2021.08.19 **zjz**
  * 首次申请状态码res.data修改 
  * 查询出来的状态为 0，老师可以进去修改 101
  * 没有申请过此岗位  102
  * 老师已提交 申请过此岗位 100

* 2021.08.20 **dynamic**
   * 给公共方法TutorInspectController，getALL ,请求路径添加/admin前缀 
   * 给研究生管理员SystemUserController，请求路径添加/graduate前缀 
   
* 2021.08.24 **wsl**
  * 给VO包下的QueryDepartmentSecretaryInit实体类中的申请表部分添加字段commit
  * 给对应的xml中 添加a.commit,返回给前端
  * 数据库status_code表 将 13 院系秘书待复审，院系主管通过 对应的inspect_describe修改为同意上分会
    (22 院系主管通过 对应的改为不同意上分会)

* 2021.08.25 **yl**
  * test

* 2021.08.29 **Rex Joush**
  * teaching_awards, research_project, academic_paper, academic_works 表的 id 字段改为自增
  * 新建 Summary(汇总信息), InventionPatent(发明专利) 实体
  * 在 `service.scientificResearchManager(社科处管理员) ` 包下新建 InventionPatentService(发明专利服务类)

* 2021.08.30 **Rex Joush**
  * guiding_student, course_teaching 表的 id 字段改为自增
  * 新建 GuidingStudent(指导学生), CourseTeaching(研究生课程教学情况) 实体

* 2021.08.31 **Rex Joush**
  * apply 表 id 字段改完 apply_id, apply_id 改完 apply_type_id
  * apply_type 表 apply_id 字段改为 apply_type_id
  * tutor_inspect, tutor_no_inspect 表 tutor_id 改完 apply_id, number 改为 tutor_id
  * system_user 表 number 改为 tutor_id

* 2021.09.08 **zjz**
  * tutor_no_inspect 添加上传免审的证明材料路径字段 exemption_conditions_materials
  * research_teaching_situations 科研教学情况 拆分为下面2个字段
  * teaching_awards 科研教学奖励
  * research_projects 科研项目