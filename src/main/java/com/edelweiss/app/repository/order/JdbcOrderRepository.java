package com.edelweiss.app.repository.order;

import com.edelweiss.app.domain.Coffee;
import com.edelweiss.app.domain.CoffeeOrder;
import com.edelweiss.app.domain.IngredientRef;
import lombok.extern.slf4j.Slf4j;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Repository
public class JdbcOrderRepository implements OrderRepository
{
    private JdbcOperations jdbcOperations;

    public JdbcOrderRepository(JdbcOperations jdbcOperations)
    {
        this.jdbcOperations = jdbcOperations;
    }

    ;

    @Override
    @Transactional
    public CoffeeOrder save(CoffeeOrder order)
    {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Coffee_Order "
                        + "(delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at)"
                        + "values(?, ?, ?, ?, ?, ?, ? , ?, ?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );

        pscf.setReturnGeneratedKeys(true);
        order.setPlacedAt(new Date());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        order.getDeliveryName(),
                        order.getDeliveryStreet(),
                        order.getDeliveryCity(),
                        order.getDeliveryState(),
                        order.getDeliveryZip(),
                        order.getCcNumber(),
                        order.getCcExpiration(),
                        order.getCcCVV(),
                        order.getPlacedAt()
                )
        );

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        log.info("Order ID: {}", orderId);
        order.setId(orderId);

        List<Coffee> coffees = order.getCoffees();
        int i = 0;
        for (Coffee coffee : coffees)
        {
            saveCoffee(orderId, i++, coffee);
        }

        return order;
    }


    private long saveCoffee(long orderId, int orderKey, Coffee coffee)
    {
        coffee.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Coffee"
                        + "(name, created_at, coffee_order, coffee_order_key)"
                        + "values (?, ?, ?, ?)",
                Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
        );

        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        coffee.getName(),
                        coffee.getCreatedAt(),
                        orderId,
                        orderKey
                )
        );

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long coffeeId = keyHolder.getKey().longValue();
        coffee.setId(coffeeId);

        saveIngredientRefs(coffeeId, coffee.getIngredients());

        return coffeeId;
    }


    private void saveIngredientRefs(long coffeeId, List<IngredientRef> ingredientRefs)
    {
        int key = 0;
        for (IngredientRef ingredientRef : ingredientRefs)
        {
            jdbcOperations.update(
                    "insert into Ingredient_Ref (ingredient, coffee, coffee_key)"
                            + "values (?, ?, ?)",
                    ingredientRef.getIngredient(), coffeeId, key++
            );
        }
    }
}
