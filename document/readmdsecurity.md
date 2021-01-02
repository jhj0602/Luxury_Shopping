# Spring Security Login,Signup

### 프로젝트 구조
![se](https://user-images.githubusercontent.com/65895403/103454952-0e5fef00-4d2c-11eb-9b97-ba44a774c717.PNG)


## 1. 의존성 추가
Spring Security를 사용하려면 , 의존성을 추가해줘야 함 ,Thymeleaf에서 Spring Security 통합 모듈을 사용하기위한 의존성도 추가해줘야함!!
#### build.gradle
``` java
implementation 'org.springframework.boot:spring-boot-starter-security'
implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'
```
## 2. Spring Security 설정
Spring Security는 FilterChainProxy라는 이름으로 내부에 여러 Filter들이 동작하고 있습니다.
<img src="https://godekdls.github.io/images/springsecurity/securityfilterchain.png">





Redis 설치
``` termianl
brew install wget # wget이 없을 시

wget http://download.redis.io/redis-stable.tar.gz
tar xvzf redis-stable.tar.gz
cd redis-stable
make
redis-server # redis 실행

# 다른 터미널로 정상적으로 실행됐는지 확인할 때
redis-cli ping
# PONG 이 나옴
```

## 2. Django에 적용
#### [프로젝트 이름]/_\_init__.py
``` python
from __future__ import absolute_import, unicode_literals

from .celery import app as celery_app

__all__ = ('celery_app',
```

#### [프로젝트 이름]/celery.py (생성)
``` python
from __future__ import absolute_import, unicode_literals
import os
from celery import Celery

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'myproject.settings')
app = Celery('myproject')

app.config_from_object('django.conf:settings', namespace='CELERY')

app.autodiscover_tasks()

@app.task(bind=True)
def debug_task(self):
    print('Request: {0!r}'.format(self.request))
```

#### [프로젝트 이름]/settings.py
``` python
CELERY_BROKER_URL = 'redis://localhost:6379'
CELERY_ACCEPT_CONTENT = ['application/json']
CELERY_RESULT_BACKEND = 'redis://localhost:6379'
CELERY_RESULT_SERIALIZER = 'json'
CELERY_TASK_SERIALIZER = 'json'
CELERY_TIMEZONE = 'Asia/Seoul'

INSTALLED_APPS += (
    'django_celery_beat',
    'django_celery_results',
)
```

#### [앱 이름]/tasks.py (생성)
``` python
from __future__ import absolute_import, unicode_literals
from celery.decorators import task

@task(name="x_plus_y") # 예시
def x_plus_y(x, y):
    return x + y
```


## 3. 실행
1. redis 실행
``` terminal
redis-server
```
2. celery 실행
``` terminal
# 프로젝트가 있는 dir에서
celery -A [프로젝트명] worker -l info
```
3. task 실행
``` terminal
# 프로젝트가 있는 dir에서
python manage.py shell
>>> from proj.celery import debug_task
>>> debug_task.delay()
# celery를 실행한 terminal에서 log가 보일 것임
```


## 4. 내 서비스에 적용하기
##### 특정 시간마다 View에 있는 함수를 실행시키기 위함
###### <i>해당 함수는 크롤링한 정보를 바탕으로 약 10만개의 데이터들 중에 보유중인 데이터를 제외하고 모델에 저장하는 기능을 함 </i>
``` python
# tasks.py
...

from .views import save_test, lotteproduct

@task(name="save_temp")
def save_temp():
    return save_test()

@task(name="save_lottedata")
def save_lottedate():
    return lotteproduct()


# celery.py
...

app.conf.beat_schedule = {
    'add-every-100-seconds': {
        'task': 'save_temp', # 해당 name의 task를 실행
        'schedule': 100.0,  # 100초마다
    },
}
```
##### redis, celery worker을 실행 후
``` terminal
celery -A [프로젝트명] beat -l info
```

## 5. [실행결과](https://youtu.be/wAfYfT6tNiI)




<iframe width="560" height="315" src="https://www.youtube.com/embed/wAfYfT6tNiI" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
