package my.springboot.start.bootstudy.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
@State(Scope.Thread)
public class MyBenchmark {

    @Param({"100","1000"})
    int size;

    @Benchmark
    public int sum() {
        int s = 0;
        for (int i = 0; i < size; i++) {
            s += i;
        }
        return s;
    }

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .detectJvmArgs()
                .build();
        new Runner(opt).run();
    }
}


