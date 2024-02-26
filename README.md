java project

```shell 
#查看nginx进程
ps aux #可以查看系统中所有的进程
ps -le #可以查看系统中所有的进程，而且还能看到进程的父进程的 PID 和进程优先级；
ps -l #只能看到当前 Shell 产生的进程；
ps -ef | grep nginx  #查看并筛选跟nginx有关的进程，该进程名可以是全部或者部分
ps -ef | grep tomCat
ps -ef | grep mysql
查看linux版本 rpm -qa | grep nginx
```