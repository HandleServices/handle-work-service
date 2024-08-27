CREATE TABLE works
(
    id             bigserial PRIMARY KEY,
    worker_id      uuid           NOT NULL,
    value          numeric(10, 2) NOT NULL,
    name           varchar(50)    NOT NULL,
    description    text,
    estimated_time time           NOT NULL,
    enable         boolean        NOT NULL DEFAULT TRUE,
    created_at     timestamp      NOT NULL DEFAULT NOW(),
    updated_at     timestamp               DEFAULT NULL
);

CREATE FUNCTION update_timestamp_before_work_update() RETURNS trigger AS
/**
 * Function: update_timestamp_before_work_update
 *
 * Description:
 *   This trigger function is executed before an update on the `works` table.
 *   It automatically sets the `updated_at` column to the current timestamp (`NOW()`)
 *   whenever any row in the `works` table is updated.
 *
 * Usage:
 *   This function should be used with a `BEFORE UPDATE` trigger on the `works` table.
 *   The trigger ensures that the `updated_at` column always reflects the most recent
 *   update time for a row.
 *
 * Details:
 *   - The function first checks if the trigger is being executed on the `works` table
 *     using `tg_table_name`.
 *   - If the condition is true, the `updated_at` column of the `NEW` record is set to
 *     the current timestamp.
 *   - The updated `NEW` record is then returned to finalize the update operation.
 *
 * Returns:
 *   - A modified `NEW` record with the `updated_at` field updated.
 *
 */
$$
BEGIN
    IF
        tg_table_name = 'works' THEN
        -- Update the updated_at column with the current timestamp
        NEW.updated_at := NOW();
    END IF;
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;

CREATE TRIGGER update_timestamp_before_work_update_trigger
    BEFORE UPDATE
    ON works
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp_before_work_update();
