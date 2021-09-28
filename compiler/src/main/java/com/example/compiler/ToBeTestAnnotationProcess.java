package com.example.compiler;

import com.example.module_annotation.ToBeTest;
import com.google.auto.service.AutoService;

import java.util.Set;
import java.util.function.Consumer;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.example.module_annotation.ToBeTest")
@AutoService(Processor.class)
public class ToBeTestAnnotationProcess extends AbstractProcessor {

    private void note(String msg) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE , msg);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        // 获取被@EnumInnerConstant注解标记的所有元素(可能是类、变量、方法等等)
        Set<? extends Element> elementSet = roundEnvironment.getElementsAnnotatedWith(ToBeTest.class);

        elementSet.forEach(new Consumer<Element>() {
            @Override
            public void accept(Element element) {

            }
        });

        return true;
    }

    private void dealElement(Element element) {

    }

}
