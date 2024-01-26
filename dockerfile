FROM bellsoft/liberica-openjdk-alpine:11.0.16.1-1

COPY ./seminar1/src/main/java/ ./src

RUN mkdir ./out

RUN mkdir ./docs

RUN javac -sourcepath ./src -d ./out ./src/pkgMain/Calc.java

CMD java -classpath ./out pkgMain.Calc ; javadoc -locale ru_RU -encoding utf-8 -docencoding cp1251 -d ./docs -sourcepath ./src -cp ./out -subpackages pkgFunctions pkgMain


