package com.hainiu.cat.web.controller;

import com.hainiu.cat.service.PuppeteerService;
import com.hainiu.cat.util.Filter;
import com.hainiu.cat.web.vo.JsonReturn;
import com.hainiu.cat.web.vo.SummaryVO;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * create by biji.zhao on 2020/11/11
 */
@RequestMapping("/puppeteer")
@RestController
public class PuppeteerController {

    @Autowired
    PuppeteerService puppeteerService;

    @RequestMapping("/save")
    public JsonReturn login() {
        return JsonReturn.successInstance();
    }

    public static void main(String[] args) {
//        collectTest();
//        functionTest();
        Stream.generate(Math::random).limit(10).forEach(System.out::println);

        // peek 是没有返回值的 所以可以对集合进行任意操作
        Stream.of(1, 2, 3, 4, 5, 6).peek(e -> e = e * 200).skip(1).limit(3).forEach(System.out::println);
    }

    private static void functionTest() {
        // 函数式编程接口，因此只有入参，没有出参
        Consumer consumer = System.out::println;

        Consumer f1 = System.out::println;
        Consumer f2 = n -> System.out.println(n +"-f2");

        f1.andThen(f2).accept("test1");

        // function 有输入也有输出
        Function<Integer, Integer> f = s -> s = s + 1;

        Function<Integer, Integer> g = s -> s * 2;

        // 先执行g 再执行f 并把g的输出作为f的输入
        System.out.println(f.compose(g).apply(1));

        System.out.println(f.andThen(g).apply(1));

        System.out.println(Function.identity().apply("a"));

        predicateTest();
    }

    private static void predicateTest() {
        Predicate<String> p = o -> o.equals("test");
        Predicate<String> g = o -> o.startsWith("t");
        System.out.println(p.negate().test("test"));
        System.out.println(p.and(g).test("test"));
        System.out.println(p.or(g).test("ta"));
    }

    private static void collectTest() {
        List<SummaryVO> summaryVOS = Filter.listFactory(10, () -> {
            SummaryVO summaryVO = new SummaryVO();
            summaryVO.setTotalCount(RandomUtils.nextInt());
            return summaryVO;
        });
        Integer sumCount = summaryVOS.stream()
                .map(SummaryVO::getTotalCount)
                .reduce(0, Integer::sum);
        summaryVOS.stream()
                .map(SummaryVO::getTotalCount)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);
        String join = summaryVOS.stream()
                .map(e -> String.valueOf(e.getTotalCount()))
                .collect(Collectors.joining("|"));
        Integer reduceSum = summaryVOS.stream()
                .filter(e -> e.getUserVO() != null)
                .map(e -> e.getUserVO().getId())
                .reduce(0, Integer::sum);
        boolean b = summaryVOS.stream()
                .filter(e -> !CollectionUtils.isEmpty(e.getUserVOList()))
                .flatMap(e -> e.getUserVOList().stream())
                .filter(e -> !StringUtils.isEmpty(e.getPassword()))
                .map(e -> e.getPassword().toLowerCase())
                .distinct()
                .anyMatch("xxx"::contains);
        List<SummaryVO> treeSummaryList = summaryVOS.stream()
                .collect(collectingAndThen(
                        toCollection(
                                () -> new TreeSet<>(comparing(SummaryVO::getTotalCount))), ArrayList::new)
                );
        LinkedHashMap<Integer, List<SummaryVO>> LinkHashMap = summaryVOS.stream()
                .collect(groupingBy(SummaryVO::getTotalCount, LinkedHashMap::new, toList()));
        summaryVOS.stream()
                .map(SummaryVO::getSummaryMap)
                .filter(Objects::nonNull)
                .forEach(map -> map.forEach((k, v) -> System.out.println(k + v)));
        Set<SummaryVO> set = new TreeSet<>((one, two) -> Objects.equals(one.getTotalCount(), two.getTotalCount()) ? 0 : 1);
        set.addAll(summaryVOS);
        set.stream()
                .map(SummaryVO::getTotalCount)
                .skip(1).limit(5)
                .reduce((x, y) -> x - y)
                .ifPresent(System.out::println);
    }
}
