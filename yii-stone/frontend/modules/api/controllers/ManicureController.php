<?php

namespace frontend\modules\api\controllers;
use \backend\models\base\ManicurePedicure ;
class ManicureController extends \yii\rest\ActiveController
{
    public $modelClass = 'backend\models\base\ManicurePedicure';

    public function actionSearch($one='')
    {



        $id = \Yii::$app->request->post('id');

        $model = ManicurePedicure::find()
            ->joinWith('user')
            ->where(['manicure_pedicure.user_id' => $id])
            ->orderBy(['id' => SORT_DESC]);
            if ($one <> '' ) $model = $model->one(); else  $model = $model->all();
        if(count($model)){
            return $model;
            //return ['id' => $user->id , 'name' => $user->profile->name , 'email' => $user->email , 'phone' => $phone];
        }else{
            return ['success'=>0,'id' => 0   ];
        }
    }

}