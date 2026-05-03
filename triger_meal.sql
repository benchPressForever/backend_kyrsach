CREATE OR REPLACE FUNCTION recalc_meal_nutrients()
RETURNS TRIGGER AS $$
DECLARE
    total_calories DOUBLE PRECISION;
    total_protein DOUBLE PRECISION;
    total_fat DOUBLE PRECISION;
    total_carbs DOUBLE PRECISION;
BEGIN
    SELECT 
        COALESCE(SUM(f.calories_per_100 * COALESCE(mf.serving_size, 0) / 100), 0),
        COALESCE(SUM(f.protein_per_100 * COALESCE(mf.serving_size, 0) / 100), 0),
        COALESCE(SUM(f.fat_per_100 * COALESCE(mf.serving_size, 0) / 100), 0),
        COALESCE(SUM(f.carbs_per_100 * COALESCE(mf.serving_size, 0) / 100), 0)
    INTO 
        total_calories, total_protein, total_fat, total_carbs
    FROM meal_food AS mf
    JOIN food AS f ON mf.food_id = f.id
    WHERE mf.meal_id = COALESCE(NEW.meal_id, OLD.meal_id);
    
    UPDATE meal 
    SET 
        calories = total_calories,
        protein = total_protein,
        fat = total_fat,
        carbs = total_carbs
    WHERE id = COALESCE(NEW.meal_id, OLD.meal_id);
    
    RETURN COALESCE(NEW, OLD);
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_recalc_meal_nutrients_insert
    AFTER INSERT ON meal_food
    FOR EACH ROW
    EXECUTE FUNCTION recalc_meal_nutrients();

CREATE TRIGGER trigger_recalc_meal_nutrients_update
    AFTER UPDATE OF serving_size, food_id ON meal_food
    FOR EACH ROW
    EXECUTE FUNCTION recalc_meal_nutrients();

CREATE TRIGGER trigger_recalc_meal_nutrients_delete
    AFTER DELETE ON meal_food
    FOR EACH ROW
    EXECUTE FUNCTION recalc_meal_nutrients();