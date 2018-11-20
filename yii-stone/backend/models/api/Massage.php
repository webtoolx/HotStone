<?php

namespace backend\models\api;

use Yii;
use yii\behaviors\TimestampBehavior;

/**
 * This is the model class for table "massage".
 *
 * @property int $id
 * @property int $branch_id
 * @property int $user_id
 * @property string $massage_do_you_prefer
 * @property string $how_do_you_prefer_your_massage_pressure
 * @property int $are_you_pregnant
 * @property string $client_signature
 * @property string $tech_signature
 * @property string $date_signature
 * @property int $created_by
 * @property int $updated_by
 * @property int $created_at
 * @property int $updated_at
 * @property int $lock
 * @property int $reted
 * @property int $rate
 * @property int $deleted_by
 * @property int $deleted_at
 *
 * @property User $createdBy
 * @property User $updatedBy
 * @property User $user
 */
class Massage extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'massage';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['branch_id', 'user_id','how_do_you_prefer_your_massage_pressure','massage_do_you_prefer'], 'required'],
            [['branch_id', 'user_id', 'created_by', 'updated_by', 'created_at', 'updated_at', 'rate', 'deleted_by', 'deleted_at'], 'integer'],
            [['how_do_you_prefer_your_massage_pressure', 'client_signature', 'tech_signature'], 'string'],
            [['massage_do_you_prefer', 'date_signature','client_review'], 'string', 'max' => 255],
            [[ 'lock', 'reted'], 'string', 'max' => 1],
            [['are_you_pregnant'], 'default', 'value' => 0],
            [['created_by'], 'exist', 'skipOnError' => true, 'targetClass' => User::className(), 'targetAttribute' => ['created_by' => 'id']],
            [['updated_by'], 'exist', 'skipOnError' => true, 'targetClass' => User::className(), 'targetAttribute' => ['updated_by' => 'id']],
            [['user_id'], 'exist', 'skipOnError' => true, 'targetClass' => User::className(), 'targetAttribute' => ['user_id' => 'id']],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => Yii::t('stone', 'ID'),
            'branch_id' => Yii::t('stone', 'Branch ID'),
            'user_id' => Yii::t('stone', 'User ID'),
            'massage_do_you_prefer' => Yii::t('stone', 'Massage Do You Prefer'),
            'how_do_you_prefer_your_massage_pressure' => Yii::t('stone', 'How Do You Prefer Your Massage Pressure'),
            'are_you_pregnant' => Yii::t('stone', 'Are You Pregnant'),
            'client_signature' => Yii::t('stone', 'Client Signature'),
            'tech_signature' => Yii::t('stone', 'Tech Signature'),
            'date_signature' => Yii::t('stone', 'Date Signature'),
            'created_by' => Yii::t('stone', 'Created By'),
            'updated_by' => Yii::t('stone', 'Updated By'),
            'created_at' => Yii::t('stone', 'Created At'),
            'updated_at' => Yii::t('stone', 'Updated At'),
            'lock' => Yii::t('stone', 'Lock'),
            'reted' => Yii::t('stone', 'Reted'),
            'rate' => Yii::t('stone', 'Rate'),
            'deleted_by' => Yii::t('stone', 'Deleted By'),
            'deleted_at' => Yii::t('stone', 'Deleted At'),
        ];
    }
    public function behaviors()
    {
        return [
            'timestamp' => [
                'class' => TimestampBehavior::className(),
                'createdAtAttribute' => 'created_at',
                'updatedAtAttribute' => 'updated_at',
                'value' => time(),
            ]
        ];
    }
    /**
     * @return \yii\db\ActiveQuery
     */
    public function getCreatedBy()
    {
        return $this->hasOne(User::className(), ['id' => 'created_by']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUpdatedBy()
    {
        return $this->hasOne(User::className(), ['id' => 'updated_by']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUser()
    {
        return $this->hasOne(User::className(), ['id' => 'user_id']);
    }

    /**
     * @inheritdoc
     * @return MassageQuery the active query used by this AR class.
     */
    public static function find()
    {
        return new MassageQuery(get_called_class());
    }
    public function fields()
    {
        $fields = parent::fields();
        $fields['created_at'] = function() {
            return date('d-m-Y',($this->created_at)) ; ;
        }; 
        return $fields;
    }
    
}
