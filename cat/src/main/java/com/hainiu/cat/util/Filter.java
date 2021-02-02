package com.hainiu.cat.util;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.hainiu.cat.web.codeStudy.AliYunSmsDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * create by biji.zhao on 2020/12/10
 */
public class Filter {

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate){
        if (CollectionUtils.isEmpty(list)){
            return Lists.newArrayList();
        }
        return Lists.newArrayList(
                list.stream().filter(predicate).collect(Collectors.toList())
        );
    }

    public static <T> void consumer(List<T> list, Consumer<T> consumer){
        if (CollectionUtils.isEmpty(list)){
            return ;
        }
        list.forEach(consumer);
    }

    public static <T> List<T> listFactory(int count, Supplier<T> supplier){
        List<T> result = new ArrayList<>(count);
        for(int i = 0; i < count; i++){
            result.add(supplier.get());
        }
        return result;
    }

    public static <T> List<String> function(List<T> list, Function<T, String> function){
        if (CollectionUtils.isEmpty(list)){
            return Lists.newArrayList();
        }
        return Lists.newArrayList(list.stream()
                .map(function).collect(Collectors.toList()));
    }


    public static void main(String[] args) {

        List<AliYunSmsDTO> list = Lists.newArrayList();
        for (int i = 0; i < 4; i++) {
            AliYunSmsDTO yunSmsDTO = new AliYunSmsDTO();
            yunSmsDTO.setTemplateCode("sdcsdc"+ i);
            list.add(yunSmsDTO);
        }
        List<AliYunSmsDTO> dtos = Filter.filter(list, o -> o.getTemplateCode().contains("2"));
        Filter.consumer(list, p -> p.setTemplateCode("ewqeqeq"));
        Filter.consumer(list, System.out::println);

        Supplier<AliYunSmsDTO> supplier = () -> {
            AliYunSmsDTO yunSmsDTO = new AliYunSmsDTO();
            yunSmsDTO.setTemplateCode("sqwdwqd");
            return yunSmsDTO;
        };
        List<AliYunSmsDTO> dtoList = Filter.listFactory(5, supplier);

        Function<AliYunSmsDTO, String> function = o -> o.getTemplateCode() + "121";

        List<String> result = Filter.function(dtoList, function);

        Filter.consumer(result, System.out::println);

    }
}
