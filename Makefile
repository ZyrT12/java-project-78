# Makefile
run-dist:
	./build/install/app/bin/app

setup:
    make -C app setup

build:
	make -C app build

run:
	.make -C app run

report:
	make -C app report

