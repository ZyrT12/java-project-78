# Makefile
run-dist:
	./build/install/app/bin/app

setup:
    ./gradlew wrapper --gradle-version 8.5
    chmod +x code/app/gradlew
    cd code/app && ./gradlew clean build
	
run:
	./gradlew run

build:
	./gradlew clean build

report:
	./gradlew jacocoTestReport

.PHONY: build
