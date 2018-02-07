/*
Copyright (C) 2018 HERE Europe B.V.
All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License"); you may
not use this file except in compliance with the License. You may obtain a
copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations
under the License.
*/

package biz.k11i.xgboost.test;

import java.util.List;

import static biz.k11i.xgboost.test.GBPredictorTestBase.PredictionTask;
import static biz.k11i.xgboost.test.GBPredictorTestBase.TestParameter;

public final class TestParameters {
    private TestParameters() {
    }

    public static List<TestParameter> testParametersForGBLinear() {
        return TestParameter.Builder.merge(
                new TestParameter.Builder()
                        .modelType("gblinear")
                        .modelNames(
                                "v40/binary-logistic",
                                "v40/binary-logitraw",
                                "v40/multi-softmax",
                                "v40/multi-softprob",
                                "v47/binary-logistic",
                                "v47/binary-logitraw",
                                "v47/multi-softmax",
                                "v47/multi-softprob")
                        .testData("agaricus.txt.0.test", false)
                        .tasks(
                                PredictionTestBase.PredictionTask.predict(),
                                PredictionTestBase.PredictionTask.predictMargin())
        );
    }

    public static List<TestParameter> testParametersForGBTree() {
        return TestParameter.Builder.merge(
                // Various prediction methods
                new TestParameter.Builder()
                        .modelType("gbtree")
                        .modelNames(
                                "v40/binary-logistic",
                                "v40/binary-logitraw",
                                "v40/multi-softmax",
                                "v40/multi-softprob",
                                "v47/binary-logistic",
                                "v47/binary-logitraw",
                                "v47/multi-softmax",
                                "v47/multi-softprob")
                        .testData("agaricus.txt.0.test", false)
                        .tasks(
                                PredictionTask.predict(),
                                PredictionTask.predictWithNTreeLimit(1),
                                PredictionTask.predictMargin(),
                                PredictionTask.predictLeaf(),
                                PredictionTask.predictLeafWithNTree(2)),

                // predictContribution
                new TestParameter.Builder()
                        .modelType("gbtree")
                        .modelNames(
                                "v47/binary-logistic",
                                "v47/binary-logitraw")
                        .testData("agaricus.txt.0.test", false)
                        .tasks(
                                PredictionTask.predictContribution(),
                                PredictionTask.predictContributionWithNTree(2)),

                // predictSingle method
                new TestParameter.Builder()
                        .modelType("gbtree")
                        .modelNames(
                                "v40/binary-logistic",
                                "v40/binary-logitraw",
                                "v47/binary-logistic",
                                "v47/binary-logitraw")
                        .testData("agaricus.txt.0.test", false)
                        .tasks(PredictionTask.predictSingle()),

                // rank:pairwise
                new TestParameter.Builder()
                        .modelType("gbtree")
                        .modelNames("v47/rank-pairwise")
                        .testData("mq2008.test", false)
                        .tasks(
                                PredictionTask.predict(),
                                PredictionTask.predictSingle()),

                // Binary classification
                new TestParameter.Builder()
                        .modelType("gbtree")
                        .modelNames("spark/agaricus")
                        .testData("agaricus.txt.1.test", true)
                        .tasks(PredictionTask.predict()),

                // Regression
                new TestParameter.Builder()
                        .modelType("gbtree")
                        .modelNames("spark/housing")
                        .testData("housing.test", true)
                        .tasks(PredictionTask.predict()),

                // Multi-class classification
                new TestParameter.Builder()
                        .modelType("gbtree")
                        .modelNames("spark/iris")
                        .testData("iris.test", true)
                        .tasks(PredictionTask.predict()),

                // Sparse data
                new TestParameter.Builder()
                        .modelType("gbtree")
                        .modelNames("v47/sms-spam")
                        .testData("sms-spam.test", false)
                        .tasks(
                                PredictionTask.predict(),
                                PredictionTask.predictLeaf())
        );
    }
}
