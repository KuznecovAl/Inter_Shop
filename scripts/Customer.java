package z19;

import org.apache.log4j.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends Thread {

    private String name;
    private Logger logger;

    /*конструктор покупателя*/
    public Customer(String name) {
        this.name = name;
        /*создаем логгер для каждого покупателя пишем в оттдельный файл и на консоль(закоментировано)*/
        this.logger = Logger.getLogger(name);
        try {
            logger.addAppender(new FileAppender(new PatternLayout("%m%n"), "log//" + this.name + ".txt"));
            //logger.addAppender(new ConsoleAppender(new PatternLayout("%m%n"),"System.out"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*стартуем покупателя*/
        start();
    }

    /*метод создания произвольного покупателя - имена берем из CustNames.txt*/
    public static Customer newRnd() {
        String name, lastname;
        ArrayList<String> al = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("resourses//CustNames.txt"));
            try {
                while ((name = br.readLine()) != null) al.add(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        name = al.get((int) (Math.random() * al.size()));
        lastname = al.get((int) (Math.random() * al.size()));
        return new Customer(name.substring(0, 10).trim() + " " + lastname.substring(10, lastname.length()));
    }

    /*переопределяем toString*/
    @Override
    public String toString() {
        return name;
    }


    /*тут без коментариев и так логируется все что можно*/
    @Override
    public void run() {
        logger.info("Покупатель " + name + " зашел в магазин.");

        logger.info(name + " взял пустую корзину.");
        HashMap<Good, Integer> basket = new HashMap<>();

        logger.info(name + " размышляет над покупками.");
        int count_goods = (int) (Math.random() * 10);//ммм скольж мне товаров взять?

        if (count_goods == 0) {
            logger.info(name + " ужаснувшись ценам покидает магазин без покупок.");
            Thread.currentThread().stop();//??????????????????????????????????????????????????????????????

        } else {
            logger.info(name + " составил список из " + count_goods + " товаров.");
            logger.info(name + " обходит магазин и набирает товары в корзину.");
            for (int i = 0; i < count_goods; i++) {
                try {
                    Thread.sleep(50);//задержка типа ходит по магазину
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Good good = Good.newRnd();  //кидает произвольный товар в корзину
                logger.info(name + " взял " + good.getName());
                basket.put(good, basket.containsKey(good) ? basket.get(good) + 1 : 1);
            }

            logger.info(name + " проходит к кассам.");//прыгает в очередь произвольной кассы :-(

            //здесь обращаемся к синхронизированномуметоду кассы
            Receipt rec = Main.cashboxes.get((int) (Math.random() * (Main.cashboxes.size()))).customerService(name, basket);

            logger.info(name + " обслуживается кассой №" + rec.getCasse_number() + "---->см. лог кассы.");
            logger.info(name + " получил чек");
            logger.info(rec.checkPrinter());


            logger.info(name + " довольный покупками покидает магазин!");
        }
    }
}
