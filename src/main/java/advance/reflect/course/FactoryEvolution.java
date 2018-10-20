package advance.reflect.course;

//工厂模式演化史
public class FactoryEvolution {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //new 操作符在通过接口获取实例对象时的耦合性

        //1, 直接创建对象
        Message news1 = new News();
        Message email1 = new Email();

        //2, 通过static方法来构建不同子类实例，且传递指定参数
        Message news2 = createMessageDirect("news");
        Message email2 = createMessageDirect("email");

        //3, 将static方法提取到一个外部类中定义，该类为工厂类
        Message news3 = MessageFactory.createMessageByNew("news");
        Message email3 = MessageFactory.createMessageByNew("emaol");

        //4, 工厂类通过反射创建实例
        //从1-4,client客户端都需要指定具体参数给这些"工厂"方法
        Message news4 = MessageFactory.CreateMessageByReflect("advance.reflect.course.News");
        Message email4 = MessageFactory.CreateMessageByReflect("advance.reflect.course.Email");

        news4.print("hello");
        email4.print("hello");

    }

    //以方法的形式创建Message，且通过参数type来区分
    public static Message createMessageDirect(String type) {
        if ("news".equalsIgnoreCase(type)) {
            return new News();
        } else if ("email".equalsIgnoreCase(type)) {
            return new Email();
        } else {
            return null;
        }
    }
}

//工厂类
class MessageFactory {

    //通过new 关键字来创建各个子类
    //当子类增加时，需要不断更新该工厂方法
    public static Message createMessageByNew(String type) {
        if ("news".equalsIgnoreCase(type)) {
            return new News();
        } else if ("email".equalsIgnoreCase(type)) {
            return new Email();
        } else {
            return null;
        }
    }

    //通过反射构造子类实例，参数为类全路径名,当子类扩充时，无需改变该方法
    public static Message CreateMessageByReflect(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName(className);
        Object instance = aClass.newInstance();
        if (instance instanceof Message) {
            return (Message) instance;
        } else {
            return null;
        }
    }

}

interface Message {
    void print(String message);
}

class News implements Message {

    @Override
    public void print(String message) {
        System.out.println("news " + message);
    }
}

class Email implements Message {

    @Override
    public void print(String message) {
        System.out.println("email " + message);
    }
}

