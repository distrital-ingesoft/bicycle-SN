FROM honkit/honkit:v4

WORKDIR /docs
VOLUME  /docs

EXPOSE 4000

RUN npm install -g honkit-plugin-todo 
RUN npm install -g honkit-plugin-plantuml-server

ENTRYPOINT [ "honkit", "serve" ]
