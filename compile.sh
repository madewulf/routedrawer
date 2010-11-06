rm -rf build
mkdir build
javac -d build src/net/multitasked/processing/*.java -cp core.jar
cp -rf deps/processing build
cp -R data build
cp data/index.html build/
cd build
jar cf routedrawer.jar *
