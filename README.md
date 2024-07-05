# プロジェクト名

credit-manage-server

## 概要

クレジットカード利用明細を管理する WEB サービス

## 説明

目的: 毎月の固定費, 食費, 外食費, その他などの使用する金額を決めている家庭において複数のクレジットカードを用いた場合の請求管理をしたい

- ログイン者によってクレジット会社のマスタ情報を登録
- アップロードした CSV から利用明細をカテゴリ分け
- カテゴリ分けしたデータを DB 保存しクレジットカード会社ごとに検索可能
- カテゴリ別合算請求額と支払い総額を支払日ごとに算出
- DB 保存期間は 6 ヶ月とする

## 関連技術

- client: React + TypeScript + Vite（[参照](https://github.com/bravecol/credit-manage-client)）
  - Netlify へデプロイ予定
- server: Java + Spring Boot（[本プロジェクト](https://github.com/bravecol/credit-manage-server)）
  - Firebase へデプロイ予定
