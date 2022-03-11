# deneme restful-api w/ docker

- build DockerFile<br>
```docker build . -t account:1.0```

- run DockerFile<br>
```docker run --name account -d -p 9090:8080 account:1.0```

# docker için ek komutlar
- start a container<br>
```docker container start <container-id>```

- stop a container<br>
```docker stop <container-id>```

- remove a container<br>
```docker container rm <container-id>```

# spring boot için ek bilgiler<br>
- Spring 4.3 ve üzeri versiyonlarda, constructor değişkenleri<br>
private final (w/ Kotlin private val) yapılırsa, siz contructor'ı @Autowired<br>
işaretlemeseniz bile @Autowired olarak tanımlanır.
