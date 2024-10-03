package ru.stas.NauJava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.stas.NauJava.Service.ProductService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.stream.Collectors;


@Component
public class CommandProcessor
{
    private final ProductService productService;
    @Autowired
    public CommandProcessor(ProductService productService)
    {
        this.productService = productService;
    }
    public void processCommand(String input) throws ParseException {
        String[] cmd = input.split(" ");
        switch (cmd[0])
        {
            case "create" ->
            {
                productService.createProduct(Long.valueOf(cmd[1]), cmd[2], Double.parseDouble(cmd[3]));
                System.out.println("Продукт успешно добавлен...");
            }
            case "find" ->
            {
                var product = productService.findById(Long.valueOf(cmd[1]));
                if (product!= null)
                    System.out.println("Продукт успешно найден:\n" + product.toString());
                else
                    System.out.println("Продукт успешно НЕнайден:");

            }
            case "delete" ->
            {
                productService.deleteById(Long.valueOf(cmd[1]));
                System.out.println("Продукт успешно удалён");
            }
            case "add-date" ->
            {
                productService.addDateOfEating(Long.valueOf(cmd[1]));
                System.out.println("Записали,что вы съели продукт");
            }
            case "range-prod" ->
            {
                var idsString = Arrays.stream(Arrays.copyOfRange(cmd, 1, cmd.length-2));
                var ids = idsString.map(Long::valueOf).collect(Collectors.toList());

                DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                var fromDate = df.parse(cmd[cmd.length-2]) ;
                var toDate = df.parse(cmd[cmd.length-1]);
                var info = productService.findAllProductsDateEatingInRange(ids, fromDate, toDate);
                System.out.println("Даты, когда вы ели" + "\n" + info.toString() + "\n");
            }

            default -> System.out.println("Введена неизвестная команда...");
        }
    }
}
