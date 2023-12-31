# Spring Batch Tasklet の中で Chank を使用する

- Spring Batch には Chunk と Tasklet の二つの主要なモデルがあります。Chunk は中間コミットを行い、Tasklet は一括コミットを行う仕様です。
- 実務上の要件で、Tasklet で実装しつつ、Chunk モデルの利点を活用し、リカバリー処理を単純化する必要がある場合があります。そのため、Tasklet コンポーネント内で Chunk コンポーネントを使用するサンプルコードを作成しました。
- このサンプルは、例えば発注管理システムから CRM（顧客関係管理）システムへのデータ連携のケースを想定しています。
- また、複数の CSV ファイルやデータベースの内容を組み合わせて他のシステムへ連携するようなシナリオでは、Chunk モデルだけでは対応が難しいため、Tasklet の利用が適しています。

# 使用技術

- Java
- Spring Framework
- Spring Batch
- MySQL
- Spring Data JPA
- Maven

# 機能

- CSV の読み込みとデータベースへの書き込み処理

# 注意事項

- エラーを意図的に起こす実装を挟んでおります。
