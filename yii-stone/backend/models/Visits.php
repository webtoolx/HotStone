<?php

namespace backend\models;

use Yii;
use \backend\models\base\Visits as BaseVisits;

/**
 * This is the model class for table "visits_list".
 */
class Visits extends BaseVisits
{
    /**
     * @inheritdoc
     */
    public function rules()
    {
        return array_replace_recursive(parent::rules(),
	    [
            [['user_id','branch_id', 'visit_date'], 'required'],
            [['user_id','branch_id', 'rate', 'created_at', 'updated_at', 'rated','created_by', 'updated_by', 'deleted_by', 'deleted_at'], 'integer'],
            [['client_signature', 'tech_signature','comments'], 'string'],
            [['visit_date'], 'safe'],
            [[  'date_signature'], 'string', 'max' => 255],
           // [[ 'lock'], 'string', 'max' => 1],
            // [['lock'], 'default', 'value' => 0],
            // [['lock'], 'mootensai\components\OptimisticLockValidator']
        ]);
    }
	
}
