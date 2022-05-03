package advance.job.asm.annotation.source;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes({"advance.job.asm.annotation.source.MySourceAnnotation"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MySourceProcessor extends AbstractProcessor {
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager messager = processingEnv.getMessager();

        messager.printMessage(Diagnostic.Kind.NOTE, "start to use mySourceProcessor");

        Set<? extends Element> rootElements = roundEnv.getRootElements();

        for (Element root : rootElements) {
            messager.printMessage(Diagnostic.Kind.NOTE, ">>root: " + root.toString());
        }

        messager.printMessage(Diagnostic.Kind.NOTE, "annotations:");

        for (TypeElement element : annotations) {
            messager.printMessage(Diagnostic.Kind.NOTE, ">>>annotation: " + element.toString());

            Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(element);
            for (Element element1 : elementsAnnotatedWith) {
                messager.printMessage(Diagnostic.Kind.NOTE, ">>>>" + element1.toString());
            }

        }

        return true;
    }
}
