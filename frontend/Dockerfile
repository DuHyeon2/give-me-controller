FROM nginx:alpine

# 기존 html 파일 삭제
RUN rm -rf /usr/share/nginx/html/*

# 파일 복사
COPY . /usr/share/nginx/html/

# 수정한 Nginx 설정 파일을 컨테이너로 복사
COPY ./default.conf /etc/nginx/conf.d/default.conf

EXPOSE 80