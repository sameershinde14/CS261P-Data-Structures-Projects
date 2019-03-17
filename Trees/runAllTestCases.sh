make clean
make all
./postProcess . > output
cd Logs
python graph_generator.py